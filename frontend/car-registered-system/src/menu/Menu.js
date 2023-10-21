import React, { useEffect } from "react";
import "../styles/Menu.css";
import { MdOutlineHome, MdOutlineFolderCopy } from "react-icons/md";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { Button } from "antd";

function Menu() {
  const navigate = useNavigate();

  const handleHome = () => {
    navigate("/");
    navigate(0);
  };

  const handlePasswordChange = () => {
    navigate("/changepassword");
    navigate(0);
  };
  return (
    <main className="menuMain">
      <Link to={"/"}>
        <img
          style={{ width: "100%" }}
          src="https://t4.ftcdn.net/jpg/03/69/79/91/360_F_369799193_D7bhKPUKNN3FFvrmp3PqtS6pi1NCGQmJ.jpg"
        />
      </Link>

      <NavLink onClick={handleHome} to={"/"}>
        <section>
          <MdOutlineHome style={{ width: "25px", height: "25px" }} /> Anasayfa
        </section>
      </NavLink>
      {localStorage.getItem("token") ? (
        <NavLink onClick={handlePasswordChange} to={"/changepassword"}>
          <section>
            <MdOutlineFolderCopy style={{ width: "25px", height: "25px" }} />{" "}
            Şifre Değiştir
          </section>
        </NavLink>
      ) : (
        ""
      )}

      <section></section>

      {localStorage.getItem("token") ? (
        ""
      ) : (
        <>
          <NavLink to={"/login"}>
            <section>
              <MdOutlineFolderCopy style={{ width: "25px", height: "25px" }} />{" "}
              Giriş Yap
            </section>
          </NavLink>
          <NavLink to={"/register"}>
            <section>
              <MdOutlineFolderCopy style={{ width: "25px", height: "25px" }} />{" "}
              Kayıt Ol
            </section>
          </NavLink>
        </>
      )}
    </main>
  );
}

export default Menu;
