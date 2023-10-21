import React, { useEffect } from "react";
import { useFormik } from "formik";
import { Button, Input, InputLabel, MenuItem, Select } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { validationsAddNewCar } from "../components/validation/Validation";
import { addOneCar } from "../service";

const brand = [
  "BMW",
  "Mercedes",
  "Honda",
  "Audi",
  "Toyota",
  "Volkswagen",
  "Volvo",
];
const modelBrand = [
  [
    "M Serisi M235i",
    "5 Serisi 520d 2.0",
    "8 Serisi Coupe 840i 3.0 M Sport",
    "X6 40d 3.0 xDrive",
    "3 Serisi 320i 1.6 M Sport",
  ],

  [
    "CLA Serisi 200 AMG Plus",
    "A Serisi A180 Style",
    "C Serisi C200 1.5",
    "S Serisi 400d AMG",
    "CLS 300D 2.0 AMG",
  ],

  [
    "S2000 2.0",
    "Legend 3.5",
    "Integra 1.8 GS",
    "Civic Eco Elegance CVT",
    "City 1.4 LS",
  ],

  [
    "R8 5.2 FSI V10 Plus",
    "A6 2.0 FSI",
    "A7 Sportback",
    "Q5 2.0 TDI",
    "Q8 3.0 TDI",
  ],

  [
    "Corolla 1.5 Vision",
    "Auris 1.33 Life",
    "Avensis 1.6 Advance",
    "Yaris 1.5 Dream e-CVT",
    "Land Cruiser Prado 2.8 D4-D AT",
  ],

  [
    "Polo 1.0 Impression",
    "Golf 1.0 Style DSG",
    "Tiguan 1.5 Life",
    "Caddy 2.0 TDI Life",
    "Passat 1.5 Business",
    "Amarok 3.0 TDI Comfortline",
  ],

  [
    "S90 2.0 D5 Inscription Plus",
    "XC90 2.0 B5 Momentum",
    "Volvo 960 2.5",
    "XC40 1.5 T2 Plus",
    "V60 1.5 T3 Advance",
  ],
];

function AddCar() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      carName: "",
      plaka: "",
      brand: "",
      model: "",
      year: "",
    },
    validationSchema: validationsAddNewCar,
    onSubmit: async (values, bag) => {
      dispatch(
        addOneCar({
          carName: values.carName,
          modal: values.model,
          year: values.year,
          brand: values.brand,
          plate: values.plaka,
          userId: localStorage.getItem("currentUserId"),
        })
      );
      navigate("/");
    },
  });

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <section>
        <h3
          style={{ fontSize: "40px", marginRight: "850px", marginTop: "15px" }}
        >
          Araç Ekle
        </h3>
      </section>

      <section style={{ width: "650px" }}>
        <form
          style={{
            marginTop: "15px",
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
            }}
          >
            <InputLabel>
              <strong>Araç Adı</strong>
            </InputLabel>
            <Input
              placeholder="Araç adını giriniz..."
              style={{ width: "500px", marginTop: "5px", marginBottom: "5px" }}
              name="carName"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.carName}
            />
            {formik.errors.carName && (
              <div
                style={{ color: "red", fontSize: "12px", textAlign: "center" }}
              >
                {formik.errors.carName}
              </div>
            )}
          </div>

          <div
            style={{
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
            }}
          >
            <InputLabel>
              <strong>Plaka</strong>
            </InputLabel>
            <Input
              placeholder="Plakayı giriniz..."
              style={{ width: "500px", marginTop: "5px", marginBottom: "5px" }}
              name="plaka"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.plaka}
            />
            {formik.errors.plaka && (
              <div
                style={{ color: "red", fontSize: "12px", textAlign: "center" }}
              >
                {formik.errors.plaka}
              </div>
            )}
          </div>

          <div
            style={{
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
            }}
          >
            <InputLabel>
              <strong>Marka</strong>
            </InputLabel>
            <Select
              style={{ width: "500px", marginTop: "5px", marginBottom: "5px" }}
              name="brand"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.brand}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {brand.map((brand, key) => {
                return <MenuItem value={brand}>{brand}</MenuItem>;
              })}
            </Select>
            {formik.errors.brand && (
              <div
                style={{ color: "red", fontSize: "12px", textAlign: "center" }}
              >
                {formik.errors.brand}
              </div>
            )}
          </div>

          <div
            style={{
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
            }}
          >
            <InputLabel>
              <strong>Model</strong>
            </InputLabel>
            <Select
              placeholder="Modeli giriniz..."
              style={{ width: "500px", marginTop: "5px", marginBottom: "5px" }}
              name="model"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.model}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {formik.values.brand
                ? modelBrand[
                    brand.findIndex((brand) => brand === formik.values.brand)
                  ].map((model) => {
                    return <MenuItem value={model}>{model}</MenuItem>;
                  })
                : ""}
            </Select>
            {formik.errors.model && (
              <div
                style={{ color: "red", fontSize: "12px", textAlign: "center" }}
              >
                {formik.errors.model}
              </div>
            )}
          </div>

          <div
            style={{
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
            }}
          >
            <InputLabel>
              <strong>Yıl</strong>
            </InputLabel>
            <Input
              placeholder="Yılı giriniz..."
              style={{ width: "500px", marginTop: "5px", marginBottom: "5px" }}
              name="year"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.year}
            />
            {formik.errors.year && (
              <div
                style={{ color: "red", fontSize: "12px", textAlign: "center" }}
              >
                {formik.errors.year}
              </div>
            )}
          </div>

          <div style={{ marginTop: "10px", textAlign: "end", width: "500px" }}>
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
                background: "linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)",
                color: "white",
              }}
            >
              Kaydet
            </Button>
          </div>
        </form>
      </section>
    </div>
  );
}

export default AddCar;
