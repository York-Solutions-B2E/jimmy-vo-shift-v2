import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import Logout from "./Logout";
import Login from "./Login";
import {useCurrentUser} from "../hooks/useCurrentUser";
import {CurrentUser} from "../store/features/userSlice";
// ...other imports

function Header() {
    const currentUser: CurrentUser | null = useCurrentUser()

    return (
        <header>
            <Navbar bg="light" expand="lg" className="justify-content-center">
                <Nav>
                    {currentUser ? (
                        <Logout />
                    ) : (
                        <Login />
                    )}
                </Nav>
            </Navbar>
        </header>
    );
}
export default Header;