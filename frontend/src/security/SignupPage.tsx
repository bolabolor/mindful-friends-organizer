import {FormEvent, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function SignupPage() {
    const [username, setUsername] = useState<string>('')
    const [password, setPassword] = useState<string>('')
    const navigate = useNavigate()

    function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        axios.post("/api/users/signup", {username, password})
            .then(response => {
                console.log(response.data)
                navigate("/friend")
            })
            .catch(error => {
                console.error(error);
            });
    }

    return (
        <form onSubmit={onSubmit}>
            <input value={username} placeholder='username' type='text' onChange={e => setUsername(e.target.value)}/>

            <input value={password} placeholder='password' type='password' onChange={e => setPassword(e.target.value)}/>
            <button type='submit'>Signup</button>
        </form>
    )
}