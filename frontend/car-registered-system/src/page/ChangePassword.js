import React, { useEffect } from "react";
import { useFormik } from "formik";
import { InputLabel } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { EyeInvisibleOutlined, EyeTwoTone } from "@ant-design/icons";
import { Button, Input, Space } from "antd";
import { changePasswordValidation } from "../components/validation/Validation";
import { changePassword } from "../service";

function Registered() {
  const [passwordVisible, setPasswordVisible] = React.useState(false);
  const changePasswordMessage = useSelector(
    (state) => state.user.changePasswordMessage
  );
  const changePasswordStatus = useSelector(
    (state) => state.user.changePasswordStatus
  );

  const dispatch = useDispatch();
  const navigate = useNavigate();
  console.log(changePasswordMessage);

  const formik = useFormik({
    initialValues: {
      oldPassword: "",
      password: "",
      passwordConfirm: "",
    },
    validationSchema: changePasswordValidation,

    onSubmit: async (values, bag) => {
      await dispatch(
        changePassword({
          oldPassword: values.oldPassword,
          newPassword: values.password,
        }),
        (formik.values.oldPassword = ""),
        (formik.values.password = ""),
        (formik.values.passwordConfirm = "")
      );
    },
  });

  return (
    <main
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        marginTop: "15px",
      }}
    >
      <div
        style={{
          marginTop: "30px",
          height: "400px",
          backgroundColor: "white",
        }}
      >
        <section
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
          }}
        ></section>

        <section style={{ width: "650px" }}>
          <form
            onSubmit={formik.handleSubmit}
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                flexDirection: "column",
                marginTop: "50px",
              }}
            >
              <InputLabel>
                <strong
                  style={{
                    fontSize: "15px",
                    display: "flex",
                    marginTop: "8px",
                    marginBottom: "5px",
                  }}
                >
                  Mevcut Şifre
                </strong>
              </InputLabel>
              <Space
                style={{
                  width: "500px",
                  marginTop: "5px",
                  marginBottom: "5px",
                }}
                direction="vertical"
              >
                <Input.Password
                  placeholder="Şifre giriniz..."
                  name="oldPassword"
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  value={formik.values.oldPassword}
                  iconRender={(visible) =>
                    visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                  }
                />
                {formik.errors.oldPassword && (
                  <div
                    style={{
                      color: "red",
                      fontSize: "12px",
                      textAlign: "center",
                    }}
                  >
                    {formik.errors.oldPassword}
                  </div>
                )}
              </Space>

              <InputLabel>
                <strong
                  style={{
                    fontSize: "15px",
                    display: "flex",
                    marginTop: "8px",
                    marginBottom: "5px",
                  }}
                >
                  Yeni Şifre
                </strong>
              </InputLabel>
              <Space
                style={{
                  width: "500px",
                  marginTop: "5px",
                  marginBottom: "5px",
                }}
                direction="vertical"
              >
                <Input.Password
                  placeholder="Şifre giriniz..."
                  name="password"
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  value={formik.values.password}
                  iconRender={(visible) =>
                    visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                  }
                />
                {formik.errors.password && (
                  <div
                    style={{
                      color: "red",
                      fontSize: "12px",
                      textAlign: "center",
                    }}
                  >
                    {formik.errors.password}
                  </div>
                )}
              </Space>

              <InputLabel>
                <strong
                  style={{
                    fontSize: "15px",
                    display: "flex",
                    marginTop: "8px",
                    marginBottom: "5px",
                  }}
                >
                  Yeni Şifre Tekrar
                </strong>
              </InputLabel>
              <Space
                style={{
                  width: "500px",
                  marginTop: "5px",
                  marginBottom: "5px",
                }}
                direction="vertical"
              >
                <Input.Password
                  placeholder="Şifre giriniz..."
                  name="passwordConfirm"
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  value={formik.values.passwordConfirm}
                  iconRender={(visible) =>
                    visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />
                  }
                />
                {formik.errors.passwordConfirm && (
                  <div
                    style={{
                      color: "red",
                      fontSize: "12px",
                      textAlign: "center",
                    }}
                  >
                    {formik.errors.passwordConfirm}
                  </div>
                )}
              </Space>
            </div>

            <div
              style={{ marginTop: "10px", textAlign: "end", width: "500px" }}
            >
              <Button
                onClick={() => navigate("/")}
                variant="text"
                style={{
                  marginRight: "15px",
                }}
              >
                İptal Et
              </Button>

              <Button
                onClick={formik.handleSubmit}
                variant="contained"
                style={{
                  background:
                    "linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)",
                  color: "white",
                }}
              >
                Kaydet
              </Button>
            </div>
          </form>
          {changePasswordMessage ? (
            <div
              style={
                changePasswordMessage.message.includes("yanlış")
                  ? { color: "red", textAlign: "center", marginTop: "50px" }
                  : { color: "green", textAlign: "center", marginTop: "50px" }
              }
            >
              {changePasswordMessage.message}
            </div>
          ) : (
            ""
          )}
        </section>
      </div>
    </main>
  );
}

export default Registered;
