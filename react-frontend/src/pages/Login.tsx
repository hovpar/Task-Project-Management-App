import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/login', { username, password });
            console.log('Login successful:', response.data);
            navigate('/'); // Redirect to home on successful login
        } catch (error) {
            console.error('Login failed:', error);
        }
    };
    return (
        <div className="flex h-screen justify-center items-center bg-gray-100">
            <form onSubmit={handleLogin} className="bg-white p-6 rounded shadow w-80">
                <h2 className="text-xl mb-4">Login</h2>
                <input
                    type="text"
                    placeholder="Username"
                    className="border w-full mb-2 p-2"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Password"
                    className="border w-full mb-4 p-2"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button
                    type="submit"
                    className="bg-blue-500 text-white w-full py-2 rounded"
                >
                    Login
                </button>
                <label className="mt-4 text-sm text-gray-600">
                    Don't have an account? <a href="/register" className="text-blue-500">Register</a>
                </label>
            </form>
        </div>
    );
}