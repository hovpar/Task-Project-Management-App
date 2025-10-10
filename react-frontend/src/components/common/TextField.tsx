import type { InputHTMLAttributes } from 'react';

type Props = InputHTMLAttributes<HTMLInputElement> & {
    label?: string;
};

export default function TextField({ label, className = '', id, ...rest }: Props) {
    const inputId = id || rest.name || undefined;
    return (
        <div className="flex flex-col gap-1">
            {label && (
                <label htmlFor={inputId} className="text-sm font-medium text-slate-700">
                    {label}
                </label>
            )}
            <input
                id={inputId}
                className={`w-full rounded-md border border-slate-300 bg-white px-3 py-2 text-sm text-slate-900 placeholder-slate-400 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-600 ${className}`}
                {...rest}
            />
        </div>
    );
}



