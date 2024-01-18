import React, { useState } from 'react';
import { useAppDispatch } from '../store/store';
import { login } from '../store/features/userSlice';
import {useErrorMessage} from "../hooks/useError";

interface LoginFormProps {}

const Login: React.FC<LoginFormProps> = () => {
    const [email, setEmail] = useState<string>('');
    const errorMessage = useErrorMessage('user') ;

    const [password, setPassword] = useState<string>('');
    const dispatch = useAppDispatch();

    const handleLogin = async (e: any) => {
        e.preventDefault()
        dispatch(login({ email, password }))
    };

    return (
        <>
        <div>
        </div>

        <form onSubmit={handleLogin}
            className="container mt-2 mb-2 d-flex justify-content-center">
            <div className="card p-4">
                {errorMessage && (
                    <div className="alert alert-danger text-center" role="alert">
                        {errorMessage}
                    </div>
                )}
                <div className="form-group mb-3">
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        className="form-control"
                    />
                </div>
                <div className="form-group mb-3">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        className="form-control"
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Login
                </button>
            </div>
        </form>
        </>
    );
};

export default Login;
