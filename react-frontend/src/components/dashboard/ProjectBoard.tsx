import type { FormEvent } from 'react';
import { useState } from 'react';
import type { Project } from '../../types/project';
import TaskList from './TaskList';
import Button from '../common/Button';
import TextField from '../common/TextField';

type Props = {
    project: Project;
    onAddList: (title: string) => Promise<void> | void;
    onAddTask: (listId: string, title: string) => Promise<void> | void;
};

export default function ProjectBoard({ project, onAddList, onAddTask }: Props) {
    const [listTitle, setListTitle] = useState('');

    const submit = async (e: FormEvent) => {
        e.preventDefault();
        if (!listTitle.trim()) return;
        await onAddList(listTitle.trim());
        setListTitle('');
    };

    return (
        <div className="flex gap-4 items-start overflow-x-auto p-4">
            {project.lists.map((list) => (
                <TaskList key={list.id} project={project} list={list} onAddTask={onAddTask} />
            ))}
            <div className="w-72 shrink-0 rounded-lg border border-dashed border-slate-300 p-3 bg-white/50">
                <form onSubmit={submit} className="flex flex-col gap-2">
                    <TextField
                        value={listTitle}
                        onChange={(e) => setListTitle(e.currentTarget.value)}
                        placeholder="New list title"
                    />
                    <Button type="submit" className="w-full">
                        Add List
                    </Button>
                </form>
            </div>
        </div>
    );
}



