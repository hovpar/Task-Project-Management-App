import type { Task } from '../../types/project';

type Props = {
    task: Task;
};

export default function TaskCard({ task }: Props) {
    return (
        <div className="rounded-md bg-white dark:bg-slate-900 border border-slate-200 dark:border-slate-700 p-3 shadow-sm">
            <div className="text-sm text-slate-900 dark:text-slate-100">{task.title}</div>
        </div>
    );
}



