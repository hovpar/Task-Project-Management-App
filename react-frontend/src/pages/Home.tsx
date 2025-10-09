
import { useNavigate } from 'react-router-dom';

export default function Home() {
    const navigate = useNavigate();

    const handleLogout = () => {
        // Clear any authentication tokens or session data here
        console.log('Logged out successfully!');
        navigate('/login'); // Redirect to login page after logout
    }



    return (
        <div className="flex h-screen justify-center items-center bg-gray-100">
            <div className="bg-white p-6 rounded shadow w-80 text-center">
                <h2 className="text-xl mb-4">Home</h2>
                <button
                    onClick={handleLogout}
                    className="bg-red-500 text-white w-full py-2 rounded"
                >
                    Logout
                </button>

            </div>
        </div>
    );
}