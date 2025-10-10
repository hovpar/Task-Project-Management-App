import type { FormEvent } from 'react';
import { useState } from 'react';
import type { Project, TaskList as TList } from '../../types/project';
import TaskCard from './TaskCard';
import Button from '../common/Button';
import TextField from '../common/TextField';

type Props = {
    project: Project;
    list: TList;
    onAddTask: (listId: string, title: string) => Promise<void> | void;
};

export default function TaskList({ project, list, onAddTask }: Props) {
    const [title, setTitle] = useState('');

    const submit = async (e: FormEvent) => {
        e.preventDefault();
        if (!title.trim()) return;
        await onAddTask(list.id, title.trim());
        setTitle('');
    };

    return (
        <div className="w-72 shrink-0 rounded-lg bg-slate-50 dark:bg-slate-800 p-3 border border-slate-200 dark:border-slate-700">
            <div className="mb-3 font-semibold text-slate-800 dark:text-slate-100">{list.title}</div>
            <div className="flex flex-col gap-2">
                {list.taskIds.map((id) => (
                    <TaskCard key={id} task={project.tasksById[id]} />
                ))}
            </div>
            <form onSubmit={submit} className="mt-3 flex flex-col gap-2">
                <TextField
                    value={title}
                    onChange={(e) => setTitle(e.currentTarget.value)}
                    placeholder="Add a task"
                />
                <Button type="submit" className="w-full">
                    Add Task
                </Button>
            </form>
        </div>
    );
}



