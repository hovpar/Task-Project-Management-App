import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleRegister = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/register", {
                username,
                password,
            });
            alert("Registration successful! Now login.");
            navigate("/login");
        } catch (err) {
            console.error("Register failed:", err);
        }
    };

    return (
        <div className="flex h-screen justify-center items-center bg-gray-100">
            <form
                onSubmit={handleRegister}
                className="bg-white p-6 rounded shadow w-80"
            >
                <h2 className="text-xl mb-4">Register</h2>
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
                    className="bg-green-500 text-white w-full py-2 rounded"
                >
                    Register
                </button>
                <label className="mt-4 text-sm text-gray-600">
                    Already have an account?{" "}
                    <a href="/login" className="text-blue-500">
                        Login
                    </a>
                </label>
            </form>
        </div>
    );
}
