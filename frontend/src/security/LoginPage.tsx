import {FormEvent, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

type Props = {
    onLogin: (username: string, password: string) => Promise<void>
}

export default function LoginPage(props: Props) {
    const [username, setUsername] = useState<string>('')
    const [password, setPassword] = useState<string>('')
    const navigate = useNavigate()

    function onSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()

        props.onLogin(username, password)
            .then(() => {navigate("/")});


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