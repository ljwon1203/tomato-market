import * as React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import AppBar from "@mui/material/AppBar";
import Button from "@mui/material/Button";
import { TextField } from "@mui/material";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import IconButton from "@mui/material/IconButton";
import AccountCircle from "@mui/icons-material/AccountCircle";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";

import { api } from "../utils/apis";

const MODE = {
  seller: "seller",
  products: "products",
  myproducts: "myproducts",
  customer: "customer",
  customerRequest: "customerRequest",
  sellerRequest: "sellerRequest",
};

const AUTH = {
  CUSTOMER: 0,
  SELLER: 1,
  ADMIN: 2,
};

const categories = [
  {
    label: "가구",
    value: 0,
  },
  {
    label: "화장품",
    value: 1,
  },
  {
    label: "스포츠",
    value: 2,
  },
  {
    label: "도서",
    value: 3,
  },
  {
    label: "가전제품",
    value: 4,
  },
];

const PRODUCT_CATRGORY = {
  FURNITURE: 0,
  BEAUTY: 1,
  SPORTS: 2,
  BOOK: 3,
  ELECTRONICS: 4,
};

const theme = createTheme();

export default function Home() {
  const navigate = useNavigate();
  const [auth, setAuth] = useState(AUTH.SELLER);
  const [mode, setMode] = useState();
  const [userId, setUserId] = useState(0);
  const [nickname, setNickname] = useState("");
  const [anchorEl, setAnchorEl] = useState();
  const [sellers, setSellers] = useState([]);
  const [products, setProducts] = useState([]);
  const [myproducts, setMyproducts] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [customerRequest, setCustomerRequest] = useState([]);
  const [sellerRequest, setSellerRequest] = useState([]);
  const [isProductModalOpen, setIsProductModalOpen] = useState(false);
  const [productName, setProductName] = useState("");
  const [productDescription, setProductDescription] = useState("");
  const [productPrice, setProductPrice] = useState(0);
  const [category, setCategory] = useState(0);
  const [isProductModalModifyMode, setIsProductModalModifyMode] =
    useState(false);
  const [updateProductId, setUpdateProductId] = useState(0);
  const [isSellerProfileModalOpen, setIsSellerProfileModalOpen] =
    useState(false);
  const [selectedSeller, setSelectedSeller] = useState();

  useEffect(() => {
    // 1. 토큰 검증
    getJWTToken();

    // 2. 유저 정보를 가져옵니다.
    getUserProfile();
  }, []);

  useEffect(() => {
    if (!userId) {
      return;
    }
    switch (mode) {
      case MODE.customer:
        getAllCustomers();
        break;
      case MODE.seller:
        getSellers();
        break;
      case MODE.customerRequest:
        getCustomerRequests();
        break;
      case MODE.sellerRequest:
        getSellersAuths();
        break;
      case MODE.myproducts:
        getMyProducts();
        break;
      case MODE.products:
        getAllProducts();
        break;
    }
  }, [mode]);

  const getJWTToken = () => {
    const jwtToken = localStorage.getItem("access_token");

    if (!jwtToken) {
      navigate("/login");
      return;
    }
    api.default.setHeadersAuthorization(jwtToken);
  };

  const getUserProfile = async () => {
    try {
      const { data } = await api.getProfile();
      setUserId(data.id);
      setNickname(data.nickname);
      setAuth(AUTH[data.role]);

      switch (AUTH[data.role]) {
        case AUTH.ADMIN:
        case AUTH.CUSTOMER:
          setMode(MODE.seller);
          break;
        case AUTH.SELLER:
          setMode(MODE.customerRequest);
          break;
      }
    } catch (e) {
      if (e) {
        api.default.deleteHeadersAuthorization();
        delete localStorage.clear();
        navigate("/login");
      }
    }
  };

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const onMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    localStorage.removeItem("access_token");
    window.location.reload();
  };

  const submitSellerAuth = () => {
    const introduce = window.prompt("판매자 소개글을 입력해주세요.");
    if (!introduce) {
      alert("한 단어 이상 입력해주세요.");
      submitSellerAuth();
      return;
    }
    const payload = {
      introduce,
    };
    try {
      api.postSellerAuth(payload);
      alert("판매자 신청이 완료되었습니다.");
      onMenuClose();
    } catch (e) {
      throw new Error(e);
    }
  };

  const handleApproveSellerRequest = async (waitingId) => {
    try {
      await api.patchSellerAuth(waitingId);
      await getSellersAuths();
    } catch (e) {
      throw new Error(e);
    }
  };

  const handleProductRegisterModalOpen = () => {
    setIsProductModalOpen(true);
  };

  const handleProductRegisterModalClose = () => {
    setIsProductModalOpen(false);
  };

  const submitNewProduct = async () => {
    const payload = {
      name: productName,
      price: productPrice,
      description: productDescription,
      productCategory: category,
    };

    try {
      await api.postProduct(payload);
      await getMyProducts();
      setIsProductModalOpen(false);
    } catch (e) {
      throw new Error(e);
    }
  };

  const getMyProducts = () => {
    api.getMyProducts().then((res) => {
      setMyproducts(() => [...res.data]);
    });
  };

  const deleteMyProduct = async (productId) => {
    try {
      await api.deleteMyProduct(productId);
      await getMyProducts();
    } catch (e) {
      throw new Error(e);
    }
  };

  const updateMyProduct = async () => {
    const payload = {
      name: productName,
      price: productPrice,
      description: productDescription,
      productCategory: category,
    };

    try {
      await api.patchProduct(updateProductId, payload);
      await getMyProducts();
      setIsProductModalOpen(false);
      setIsProductModalModifyMode(false);
    } catch (e) {
      throw new Error(e);
    }
  };

  const openProductModifyModal = (productId) => {
    setIsProductModalModifyMode(true);
    const [product] = myproducts.filter((product) => product.id === productId);
    setUpdateProductId(product.id);
    setProductName(product.name);
    setProductPrice(product.price);
    setProductDescription(product.description);
    setCategory(PRODUCT_CATRGORY[product.category.id]);
    setIsProductModalOpen(true);
  };

  const requestProductQuotation = async (productId) => {
    try {
      await api.postQuotation(productId);
      await getAllProducts();
    } catch (e) {}
  };

  const getCustomerRequests = () => {
    api
      .getQuotations({
        page: 0,
        size: 10,
      })
      .then((res) => {
        setCustomerRequest(() => [...res.data]);
      });
  };

  const getSellersAuths = () => {
    api.getSellerAuth().then((res) => {
      setSellerRequest(() => [...res.data]);
    });
  };

  const getSellers = () => {
    api
      .getSellers({
        page: 0,
        size: 10,
      })
      .then((res) => {
        setSellers(() => [...res.data]);
      });
  };

  const approveCustomerRequest = async (requestId) => {
    await api.patchQuotation(requestId);
    await getCustomerRequests();
  };

  const deleteSellerAuth = async (seller) => {
    await api.deleteSellerAuth(seller.id);
    await getSellers();
  };

  const getAllProducts = () => {
    api
      .getProducts({
        page: 0,
        size: 10,
      })
      .then((res) => {
        setProducts(() => [...res.data]);
      });
  };

  const getAllCustomers = () => {
    api.getAllCustomers({ page: 0, size: 10 }).then((res) => {
      setCustomers(() => [...res.data]);
    });
  };

  const ModeScreen = () => {
    if (mode === MODE.customer) {
      return customers.map((card) => (
        <Grid item key={card.id} xs={12} sm={6} md={4}>
          <Card
            sx={{
              height: "100%",
              display: "flex",
              flexDirection: "column",
            }}
          >
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography gutterBottom variant="h5" component="h2">
                {card.username}
              </Typography>
              <Typography>{card.nickname}</Typography>
            </CardContent>
          </Card>
        </Grid>
      ));
    } else if (mode === MODE.seller) {
      return sellers.map((card) => (
        <Grid item key={card} xs={12} sm={6} md={4}>
          <Card
            sx={{
              height: "100%",
              display: "flex",
              flexDirection: "column",
            }}
          >
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography gutterBottom variant="h5" component="h2">
                {card.user.username}
              </Typography>
            </CardContent>
            <CardActions>
              <Button
                size="small"
                variant="contained"
                onClick={() => {
                  setSelectedSeller(card);
                  setIsSellerProfileModalOpen(true);
                }}
              >
                정보보기
              </Button>
              {/* 관리자모드에서만 보이게 */}
              {auth === AUTH.ADMIN ? (
                <Button
                  size="small"
                  color="error"
                  variant="contained"
                  onClick={() => deleteSellerAuth(card)}
                >
                  권한삭제
                </Button>
              ) : (
                <></>
              )}
            </CardActions>
          </Card>
        </Grid>
      ));
    } else if (mode === MODE.customerRequest) {
      return customerRequest.map((card) => (
        <Grid item key={card.id} xs={12} sm={6} md={4}>
          <Card
            sx={{
              height: "100%",
              display: "flex",
              flexDirection: "column",
            }}
          >
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography>
                {card.user.username} 님의 {card.product.name} 구매 요청
              </Typography>
            </CardContent>
            <CardActions>
              <Button
                size="small"
                color="success"
                variant="contained"
                disabled={card.approval}
                onClick={() => approveCustomerRequest(card.id)}
              >
                {card.approval ? "승인됨" : "승인"}
              </Button>
            </CardActions>
          </Card>
        </Grid>
      ));
    } else if (mode === MODE.sellerRequest) {
      return sellerRequest.map((card) => (
        <Grid item key={card} xs={12} sm={6} md={4}>
          <Card
            sx={{
              height: "100%",
              display: "flex",
              flexDirection: "column",
            }}
          >
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography gutterBottom variant="h5" component="h2">
                {card.username}
              </Typography>
              <Typography>{card.introduce}</Typography>
            </CardContent>
            <CardActions>
              <Button
                onClick={() => handleApproveSellerRequest(card.id)}
                size="small"
                color="success"
                variant="contained"
                disabled={card.approval}
              >
                {card.approval ? "승인됨" : "승인"}
              </Button>
            </CardActions>
          </Card>
        </Grid>
      ));
    } else if (mode === MODE.products) {
      return products.map((card) => (
        <Grid item key={card.id} xs={12} sm={6} md={4}>
          <Card
            sx={{
              height: "100%",
              display: "flex",
              flexDirection: "column",
            }}
          >
            <CardContent sx={{ flexGrow: 1 }}>
              <Chip label={card.category.name}></Chip>
              <Typography gutterBottom variant="h5" component="h2">
                {card.name}
              </Typography>
              <Typography>{card.description}</Typography>
              <Typography>{card.price} 원</Typography>
            </CardContent>
            <CardActions>
              <Button
                size="small"
                color="success"
                variant="contained"
                onClick={() => requestProductQuotation(card.id)}
                disabled={card.hasQuotation}
              >
                구매요청
              </Button>
            </CardActions>
          </Card>
        </Grid>
      ));
    } else {
      return myproducts.map((card) => (
        <Grid item key={card.id} xs={12} sm={6} md={4}>
          <Card
            sx={{
              height: "100%",
              display: "flex",
              flexDirection: "column",
            }}
          >
            <CardContent sx={{ flexGrow: 1 }}>
              <Typography gutterBottom variant="h5" component="h2">
                {card.name}
              </Typography>
              <Typography>{card.price} 원</Typography>
              <Typography>{card.category.name}</Typography>
              <Typography>{card.description}</Typography>
            </CardContent>
            <CardActions>
              <Button
                size="small"
                variant="contained"
                onClick={() => openProductModifyModal(card.id)}
              >
                수정
              </Button>
              <Button
                size="small"
                color="error"
                variant="contained"
                onClick={() => deleteMyProduct(card.id)}
              >
                삭제
              </Button>
            </CardActions>
          </Card>
        </Grid>
      ));
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Dialog
        open={isProductModalOpen}
        onClose={handleProductRegisterModalClose}
        fullWidth
      >
        <DialogTitle>
          {isProductModalModifyMode ? "상품수정" : "새상품 등록"}
        </DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="상품명"
            type="name"
            fullWidth
            variant="standard"
            value={productName}
            onChange={(e) => setProductName(e.target.value)}
          />
          <TextField
            margin="dense"
            id="price"
            label="가격"
            type="price"
            fullWidth
            variant="standard"
            value={productPrice}
            onChange={(e) =>
              setProductPrice(e.target.value.replace(/[^0-9]/gi, ""))
            }
          />
          <TextField
            margin="dense"
            id="description"
            label="상품설명"
            type="description"
            fullWidth
            variant="standard"
            value={productDescription}
            onChange={(e) => setProductDescription(e.target.value)}
          />
          <TextField
            margin="normal"
            id="outlined-select-currency-native"
            select
            label="카테고리"
            value={category}
            fullWidth
            onChange={(e) => setCategory(e.target.value)}
            SelectProps={{
              native: true,
            }}
            variant="standard"
            helperText="상품의 카테고리를 선택하세요"
          >
            {categories.map((option) => (
              <option key={option.value} value={option.value}>
                {option.label}
              </option>
            ))}
          </TextField>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={handleProductRegisterModalClose}
            variant="contained"
            color="error"
          >
            닫기
          </Button>
          <Button
            type="submit"
            onClick={
              isProductModalModifyMode ? updateMyProduct : submitNewProduct
            }
            variant="contained"
            color="success"
          >
            {isProductModalModifyMode ? "수정" : "등록"}
          </Button>
        </DialogActions>
      </Dialog>
      {selectedSeller ? (
        <Dialog open={isSellerProfileModalOpen} sx={{ m: 0, p: 2 }} fullWidth>
          <DialogTitle>판매자 정보 조회</DialogTitle>

          <DialogContent dividers>
            <Typography gutterBottom>
              <strong>이름 :</strong> {selectedSeller.user.username}
            </Typography>
            <Typography gutterBottom>
              <strong>별명 :</strong> {selectedSeller.user.nickname}
            </Typography>
            <Typography gutterBottom>
              <strong>소개 :</strong> {selectedSeller.introduce}
            </Typography>
          </DialogContent>
          <DialogContent dividers>
            <Stack direction="row" spacing={1}>
              {selectedSeller.categories.map((category, index) => (
                <Chip
                  key={category}
                  color={
                    index === 0
                      ? "primary"
                      : index === 1
                      ? "secondary"
                      : index === 2
                      ? "error"
                      : index === 3
                      ? "info"
                      : "success"
                  }
                  label={category}
                />
              ))}
            </Stack>
          </DialogContent>

          <DialogActions>
            <Button
              onClick={() => {
                setIsSellerProfileModalOpen(false);
                setSelectedSeller();
              }}
              variant="contained"
              color="error"
            >
              닫기
            </Button>
          </DialogActions>
        </Dialog>
      ) : (
        <></>
      )}

      <AppBar position="relative">
        <Toolbar>
          <Typography
            variant="h6"
            color="inherit"
            component="div"
            sx={{ flexGrow: 1 }}
          >
            [
            {auth === AUTH.ADMIN
              ? "관리자"
              : auth === AUTH.SELLER
              ? "판매자"
              : "고객"}
            ] {nickname} 님
          </Typography>
          <div>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleMenu}
              color="inherit"
            >
              <AccountCircle />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorEl}
              anchorOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "right",
              }}
              open={Boolean(anchorEl)}
              onClose={onMenuClose}
            >
              <MenuItem>
                <Link
                  to="profile"
                  style={{
                    color: "rgba(0, 0, 0, 0.87)",
                    textDecoration: "none",
                  }}
                >
                  나의정보
                </Link>
              </MenuItem>
              {auth === AUTH.CUSTOMER ? (
                <MenuItem onClick={submitSellerAuth}>판매자 신청</MenuItem>
              ) : auth === AUTH.SELLER ? (
                <MenuItem onClick={handleProductRegisterModalOpen}>
                  새상품 등록
                </MenuItem>
              ) : (
                <div></div>
              )}

              <MenuItem onClick={handleLogout}>로그아웃</MenuItem>
            </Menu>
          </div>
        </Toolbar>
      </AppBar>
      <main>
        {/* Hero unit */}
        <Box
          sx={{
            bgcolor: "background.paper",
            pt: 8,
            pb: 6,
          }}
        >
          <Container maxWidth="sm">
            <Stack
              sx={{ pt: 4 }}
              direction="row"
              spacing={2}
              justifyContent="center"
            >
              {auth === AUTH.CUSTOMER ? (
                <>
                  <Button
                    onClick={() => {
                      setMode(MODE.seller);
                    }}
                    variant={mode === MODE.seller ? "contained" : "outlined"}
                  >
                    전체 판매자
                  </Button>
                  <Button
                    onClick={() => {
                      setMode(MODE.products);
                    }}
                    variant={mode === MODE.products ? "contained" : "outlined"}
                  >
                    전체 상품
                  </Button>
                </>
              ) : auth === AUTH.SELLER ? (
                <>
                  <Button
                    onClick={() => {
                      setMode(MODE.customerRequest);
                    }}
                    variant={
                      mode === MODE.customerRequest ? "contained" : "outlined"
                    }
                  >
                    고객 구매 요청
                  </Button>
                  <Button
                    onClick={() => {
                      setMode(MODE.myproducts);
                    }}
                    variant={
                      mode === MODE.myproducts ? "contained" : "outlined"
                    }
                  >
                    나의 판매 상품
                  </Button>
                </>
              ) : (
                <>
                  <Button
                    onClick={() => {
                      setMode(MODE.seller);
                    }}
                    variant={mode === MODE.seller ? "contained" : "outlined"}
                  >
                    전체 판매자
                  </Button>
                  <Button
                    onClick={() => {
                      setMode(MODE.customer);
                    }}
                    variant={mode === MODE.customer ? "contained" : "outlined"}
                  >
                    전체 고객
                  </Button>
                  <Button
                    onClick={() => {
                      setMode(MODE.sellerRequest);
                    }}
                    variant={
                      mode === MODE.sellerRequest ? "contained" : "outlined"
                    }
                  >
                    전체 권한 요청
                  </Button>
                </>
              )}
            </Stack>
          </Container>
        </Box>
        <Container sx={{ py: 8 }} maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={4}>
            <ModeScreen />
          </Grid>
        </Container>
      </main>
    </ThemeProvider>
  );
}
