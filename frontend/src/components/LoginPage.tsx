import {FormEvent, useState} from "react";
import axios from "axios";

export default function LoginPage() {
    const [username, setUsername] = useState<string>('')
    const [password, setPassword] = useState<string>('')

    function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()

        axios.post("/api/users/login", undefined, {auth: {username, password}})
            .then(response => {
                console.log(response.data)
            })

    }

    return (
        <form onSubmit={onSubmit}>
            <input value={username} placeholder='username' type='text' onChange={e => setUsername(e.target.value)}/>

            <input value={password} placeholder='password' type='password' onChange={e => setPassword(e.target.value)}/>
            <button type='submit'>Login</button>
        </form>
    )
}