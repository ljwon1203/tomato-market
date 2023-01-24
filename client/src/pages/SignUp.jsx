import * as React from "react";
import { useState } from "react";

import { api } from "../utils/apis";
import { Link as RouterLink, useNavigate } from "react-router-dom";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";

const theme = createTheme();

export default function SignUp() {
  const navigator = useNavigate();
  const [isAdminKeyInputMode, setIsAdminKeyInputMode] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    const payload = {
      username: data.get("username"),
      nickname: data.get("nickname"),
      password: data.get("password"),
      adminKey: data.get("adminkey"),
    };
    console.log("===================================");
    console.log("[LOG] 회원가입");
    console.log("===================================");
    console.log("[REQUEST] ", payload);
    try {
      // 회원가입 요청
      const response = await api.signup(payload);
      console.log("[RESPONSE] ", response);

      // 성공 시 자동 로그인 후 이동
      const { headers } = await api.login({
        username: data.get("username"),
        password: data.get("password"),
      });

      let JWTToken = headers.get("Authorization");
      if (JWTToken) {
        JWTToken = JWTToken.replace("Bearer ", "");
      }
      await localStorage.setItem("access_token", JWTToken);
      navigator("/");
    } catch (e) {
      const { response } = e;
      alert(response.data.errorMessage);
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign up
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="username"
                  label="아이디"
                  name="username"
                  autoComplete="username"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="nickname"
                  label="닉네임"
                  name="nickname"
                  autoComplete="nickname"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                />
              </Grid>
              {isAdminKeyInputMode ? (
                <Grid item xs={12}>
                  <TextField
                    fullWidth
                    name="adminkey"
                    label="AdminKey"
                    type="password"
                    id="adminkey"
                    autoComplete="adminkey"
                  />
                </Grid>
              ) : (
                <></>
              )}
              <Grid item xs={12}>
                <FormControlLabel
                  onClick={() => {
                    setIsAdminKeyInputMode(
                      (isAdminInputMode) => !isAdminInputMode
                    );
                  }}
                  control={
                    <Checkbox checked={isAdminKeyInputMode} color="primary" />
                  }
                  label="관리자로 가입할 것 입니다."
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <RouterLink
                  to={"/login"}
                  style={{
                    fontSize: "0.875rem",
                    textDecoration: "underline",
                    color: "#1976d2",
                  }}
                >
                  Already have an account? Sign in
                </RouterLink>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
