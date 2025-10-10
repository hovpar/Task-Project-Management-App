export type Task = {
    id: string;
    title: string;
    description?: string;
    createdAt: string;
};

export type TaskList = {
    id: string;
    title: string;
    taskIds: string[];
};

export type Project = {
    id: string;
    name: string;
    lists: TaskList[];
    tasksById: Record<string, Task>;
    createdAt: string;
};

export type AxiosLikeResponse<T> = { data: T };



