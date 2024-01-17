import React, { useRef } from "react";

import { useAppDispatch } from "../store/store";
import {login} from "../store/features/userSlice";

const Login = () => {
    const email = useRef<string>("");
    const password = useRef<string>("");
    const dispatch = useAppDispatch();

    return(
        <div>
            <label htmlFor="">Email:</label>
            <input
                className="border rounded-md p-2 mx-2"
                onChange={(e) => (email.current = e.target.value)}
            />
            <label htmlFor="">Password:</label>
            <input
                className="border rounded-md p-2 mx-2"
                onChange={(e) => (password.current = e.target.value)}
            />
            <button
                onClick={() => dispatch(login({ email: email.current,password: password.current }))}
                className="bg-violet-500  text-white rounded-md px-4 py-2 cursor-pointer hover:bg-violet-600 active:bg-violet-700"
            >
                Login
            </button>
        </div>

    );
}

export default Login;
