
import type { FormEvent } from 'react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '../components/common/Button';
import TextField from '../components/common/TextField';
import ProjectBoard from '../components/dashboard/ProjectBoard';
import type { Project } from '../types/project';
import { projectMockApi } from '../services/mock/projectMockApi';

export default function Home() {
    const navigate = useNavigate();
    const [project, setProject] = useState<Project | null>(null);
    const [name, setName] = useState('');

    const handleLogout = () => {
        // Clear any authentication tokens or session data here
        console.log('Logged out successfully!');
        navigate('/login'); // Redirect to login page after logout
    }

    const handleBackToProjects = () => {
        navigate('/projects'); // Redirect to projects list page
    }

    // useEffect(() => {
    //     projectMockApi.getCurrentProject().then((res) => setProject(res.data));
    // }, []);

    const createProject = async (e: FormEvent) => {
        e.preventDefault();
        if (!name.trim()) return;
        const res = await projectMockApi.createProject(name.trim());
        setProject(res.data);
        setName('');
    };

    const addList = async (title: string) => {
        if (!project) return;
        const res = await projectMockApi.addTaskList(project.id, title);
        setProject({ ...res.data });
    };

    const addTask = async (listId: string, title: string) => {
        if (!project) return;
        const res = await projectMockApi.addTask(project.id, listId, title);
        setProject({ ...res.data });
    };

    return (
        <div className="flex flex-col h-screen bg-black/50 backdrop-blur-sm">
            <div className="flex items-center justify-between border-b border-slate-200 px-4 py-3 bg-white">
                <div className="font-semibold">
                    <span
                        onClick={handleBackToProjects}
                        className="cursor-pointer text-blue-500 hover:underline"
                    >
                        Dashboard
                    </span>
                </div>
                <Button variant="danger" onClick={handleLogout}>Logout</Button>
            </div>

            <div className="flex-1 overflow-hidden">
                {!project ? (
                    <div className="h-full flex items-center justify-center bg-slate-50">
                        <form onSubmit={createProject} className="bg-white p-6 rounded-lg border border-slate-200 shadow-sm w-full max-w-sm">
                            <div className="text-lg font-semibold mb-3">Create a Project</div>
                            <div className="space-y-3">
                                <TextField
                                    value={name}
                                    onChange={(e) => setName(e.currentTarget.value)}
                                    placeholder="Project name"
                                />
                                <Button type="submit" className="w-full">Create</Button>
                            </div>
                        </form>
                    </div>
                ) : (
                    <div className="h-full overflow-auto">
                        <div className="px-4 py-3 border-b border-slate-200 bg-slate-50">
                            <div className="text-base"><span className="font-semibold">Project:</span> {project.name}</div>
                        </div>
                        <ProjectBoard project={project} onAddList={addList} onAddTask={addTask} />
                    </div>
                )}
            </div>
        </div>
    );
}