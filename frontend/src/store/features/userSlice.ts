import { createAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";

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

interface ErrorMessage {
    message: string;
    // Consider adding more properties for comprehensive error handling
}

interface CurrentUserState {
    currentUser: CurrentUser | null;
    loading: boolean;
    error: ErrorMessage | null;
}

const initialUserState: CurrentUserState = {
    currentUser: null,
    loading: false,
    error: null,
};

export const login = createAsyncThunk(
    'user/login',
    async ({ email, password }: LoginPayload, { rejectWithValue }) => {
        try {
            const response = await fetch('/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                const errorMessage = errorData.message || 'Login failed';
                throw rejectWithValue(new Error(errorMessage)); // Create and throw a new Error
            }

            return await response.json();

        } catch (error:any ) {
            const errorMessage = error.message || 'An error occurred during login';
            throw rejectWithValue(new Error(errorMessage)); // Re-throw with meaningful message
        }
    }
);

export const logout = createAction('user/logout');

export const UserSlice = createSlice({
    name: 'user',
    initialState: initialUserState,
    reducers: {
        logout: (state) => {
            // Optimization: Use Object.assign or a spread operator
            Object.assign(state, initialUserState);
        },
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
                const error: any = action.payload;
                state.loading = false;
                state.error = { message: error.message };
            });
    },
});

export default UserSlice.reducer;
