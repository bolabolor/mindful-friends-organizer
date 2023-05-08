import axios from "axios";
import {useEffect} from "react";

export default function LogoutPage() {
    useEffect(() => {
        axios.post('/api/users/logout')
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    return <div>You're logged out.</div>;
}