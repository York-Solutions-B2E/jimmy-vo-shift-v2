import {useAppSelector} from "../store/store";



export const useErrorMessage = (sliceName: string): string | null => {
    return useAppSelector((state) => (state as any)[sliceName].error?.message ?? null);
};