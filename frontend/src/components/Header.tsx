import React from "react";
import './Header.css';
import {Link, NavLink} from "react-router-dom";

type Props = {
    user: string | undefined
}
export default function Header(props: Props) {
    const authenticated = props.user !== undefined && props.user !== 'anonymousUser'

    return (
        <header className="header">
            <img className="header_img" src="https://img.freepik.com/fotos-kostenlos/rueckansicht-freunde-halten-sich-gegenseitig_23-2148662739.jpg?w=1800&t=st=1684846908~exp=1684847508~hmac=e59068c1e6b14775426766e6ccf86cab5a9c281a1cb6abc44a3e30338c6d1541" alt="header-logo"/>
            <h1 className="header_title">GoFriends - easy Organizer</h1>
            <div >
                {authenticated ? (
                    <div>
                        <Link className="headerLinkLe" to='/friend'>All Friends</Link>
                        <Link className="headerLinkRi"  to='/friend/add'>Add Friends</Link>
                    </div>
                ) : (
                    <NavLink className="headerNav" to='/login'>Login</NavLink>)}
            </div>
        </header>
    )
}