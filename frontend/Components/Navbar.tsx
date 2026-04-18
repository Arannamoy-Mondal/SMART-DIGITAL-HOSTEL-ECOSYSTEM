

const Navbar = () => {
    return (
        <div className="navbar bg-white shadow-sm">
            <div className="flex-1">
                <a className="btn btn-ghost text-xl">Smart Hostel Ecosystem</a>
            </div>
            <div className="flex-none">
                <ul className="menu menu-horizontal px-1">
                    <li><a>Signup</a></li>

                    <li><a href="">Login</a></li>
                </ul>
            </div>
        </div>
    );
};

export default Navbar;