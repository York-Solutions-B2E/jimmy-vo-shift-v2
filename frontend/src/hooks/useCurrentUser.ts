import {useAppSelector} from "../store/store";
import {CurrentUser} from "../store/features/userSlice";


export const useCurrentUser = (): CurrentUser | null => {
    return useAppSelector((state) => state.user.currentUser);
};



