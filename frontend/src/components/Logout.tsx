import React from "react";

import { useAppDispatch } from "../store/store";
import {logout} from "../store/features/userSlice";

const Logout = () => {
    const dispatch = useAppDispatch();

    return(
        <div>
            <button
                className="btn btn-outline-danger mr-2"
                onClick={() => dispatch(logout())}
            >
                Logout
            </button>
        </div>

    );
}

export default Logout;
