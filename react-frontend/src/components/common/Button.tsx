import type { ButtonHTMLAttributes } from 'react';

type Props = ButtonHTMLAttributes<HTMLButtonElement> & {
    variant?: 'primary' | 'secondary' | 'danger' | 'ghost';
};

export default function Button({ variant = 'primary', className = '', ...rest }: Props) {
    const base = 'inline-flex items-center justify-center rounded-md px-3 py-2 text-sm font-medium focus:outline-none focus-visible:ring-2 focus-visible:ring-offset-2 disabled:opacity-50 disabled:pointer-events-none';
    const variants: Record<NonNullable<Props['variant']>, string> = {
        primary: 'bg-blue-600 text-white hover:bg-blue-700 focus-visible:ring-blue-600',
        secondary: 'bg-slate-100 text-slate-900 hover:bg-slate-200 focus-visible:ring-slate-400',
        danger: 'bg-red-600 text-white hover:bg-red-700 focus-visible:ring-red-600',
        ghost: 'bg-transparent text-slate-900 hover:bg-slate-100 focus-visible:ring-slate-400',
    };
    return <button className={`${base} ${variants[variant]} ${className}`} {...rest} />;
}


