import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";

export interface CurrentUser {
    id: number;
    email: string;
    firstName: string;
    lastName: string;
    role: number;
}

interface LoginPayload {
    email: string;
    password: string;
}

interface CurrentUserState {
    currentUser: CurrentUser | null; // Use null for "no user"
    loading: boolean; // Add a loading state
    error: string | null; // Add an error state
}
const initialState: CurrentUserState = {
    currentUser: null,
    loading: false,
    error: null,
};

export const login = createAsyncThunk(
    'user/login',
    async ({ email, password }: LoginPayload, { }) => {

            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                throw new Error('Login failed');
            }

            const data = await response.json();
            return data;
    }
);

export const UserSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        // Define reducers for other actions if needed
    },
    extraReducers: (builder) => {
        builder
            .addCase(login.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(login.fulfilled, (state, action) => {
                state.loading = false;
                state.currentUser = action.payload;
            })
            .addCase(login.rejected, (state, action) => {
                state.loading = false;
                // @ts-ignore
                // state.error = action.payload.body.message;
            });
    },
});

export default UserSlice.reducer;
