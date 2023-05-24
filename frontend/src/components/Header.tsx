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
            <h1>GoFriends <span className="header_title">   your Friend Organizer</span></h1>

            <img className="header_img" src="https://img.freepik.com/fotos-kostenlos/rueckansicht-freunde-halten-sich-gegenseitig_23-2148662739.jpg?w=1800&t=st=1684846908~exp=1684847508~hmac=e59068c1e6b14775426766e6ccf86cab5a9c281a1cb6abc44a3e30338c6d1541" alt="header-logo"/>
            <div >
                {authenticated ? (
                    <div>
                        <Link className="headerLinkLe" to='/friend'>All Friends</Link>
                        <Link className="headerLinkRi"  to='/friend/add'>Add Friends</Link>
                        <NavLink to='/logout'> [ logout ]</NavLink>
                    </div>
                ) : (
                    <div>
                        <NavLink to='/signup'> [ signup ]</NavLink>
                        <NavLink to='/login'>[ login ] </NavLink>
                    </div>
                )}
            </div>
        </header>
    )
}