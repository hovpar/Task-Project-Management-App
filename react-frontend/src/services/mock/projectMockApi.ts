import type { Project, Task, TaskList, AxiosLikeResponse } from '../../types/project';

type Store = {
    currentProjectId: string | null;
    projectsById: Record<string, Project>;
};

const STORAGE_KEY = 'tpma:mock:projects';

const loadStore = (): Store => {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return { currentProjectId: null, projectsById: {} };
    try {
        return JSON.parse(raw) as Store;
    } catch {
        return { currentProjectId: null, projectsById: {} };
    }
};

const saveStore = (store: Store) => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(store));
};

const generateId = () => (typeof crypto !== 'undefined' && 'randomUUID' in crypto ? crypto.randomUUID() : Math.random().toString(36).slice(2));

let store: Store = loadStore();

const delay = (ms: number) => new Promise((res) => setTimeout(res, ms));

export const projectMockApi = {
    async createProject(name: string): Promise<AxiosLikeResponse<Project>> {
        await delay(200);
        const id = generateId();
        const project: Project = {
            id,
            name,
            lists: [],
            tasksById: {},
            createdAt: new Date().toISOString(),
        };
        store.projectsById[id] = project;
        store.currentProjectId = id;
        saveStore(store);
        return { data: project };
    },

    async getProject(projectId: string): Promise<AxiosLikeResponse<Project>> {
        await delay(150);
        const project = store.projectsById[projectId];
        if (!project) throw new Error('Project not found');
        return { data: project };
    },

    async getCurrentProject(): Promise<AxiosLikeResponse<Project | null>> {
        await delay(120);
        const id = store.currentProjectId;
        return { data: id ? store.projectsById[id] ?? null : null };
    },

    async addTaskList(projectId: string, title: string): Promise<AxiosLikeResponse<Project>> {
        await delay(200);
        const project = store.projectsById[projectId];
        if (!project) throw new Error('Project not found');
        const list: TaskList = { id: generateId(), title, taskIds: [] };
        project.lists.push(list);
        saveStore(store);
        return { data: project };
    },

    async addTask(projectId: string, listId: string, title: string): Promise<AxiosLikeResponse<Project>> {
        await delay(200);
        const project = store.projectsById[projectId];
        if (!project) throw new Error('Project not found');
        const list = project.lists.find((l) => l.id === listId);
        if (!list) throw new Error('List not found');
        const task: Task = { id: generateId(), title, createdAt: new Date().toISOString() };
        project.tasksById[task.id] = task;
        list.taskIds.push(task.id);
        saveStore(store);
        return { data: project };
    },
};


