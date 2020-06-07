import request from "@/utils/request";

export const login = (data) => {
  return request({
    url: "member/signin",
    method: "post",
    data,
  });
};

export const join = (data) => {
  return request({
    url: "member/signup",
    method: "post",
    data,
  });
};

export const acajoin = (data) => {
  return request({
    url: "member/academy-signup",
    method: "post",
    data,
  });
};

export const pay = (data) => {
  return request({
    url: "pay/ready",
    method: "post",
    data,
  });
};

export const paySuccess = (data) => {
  return request({
    url: "pay/paySuccess",
    method: "post",
    data,
  });
};

export const requestEmail = (data) => {
  return request({
    url: "verify/sendEmail",
    method: "post",
    data,
  });
};

export const emailCheck = (data) => {
  return request({
    url: "verify/checkCode",
    method: "post",
    data,
  });
};

export const findPassword = (data) => {
  return request({
    url: "member/findPassword",
    method: "post",
    data,
  });
};

export const updateProfile = (data) => {
  return request({
    url: "member",
    method: "put",
    data,
  });
};

export const passwordCheck = (data) => {
  return request({
    url: "member/checkPassword",
    method: "post",
    data,
  });
};

export const retire = (data) => {
  return request({
    url: "member",
    method: "delete",
    data,
  });
};

export const readReview = (data) => {
  return request({
    url: "review/" + data + "/academy",
    method: "get"
  });
};

export const writeReview = (data) => {
  return request({
    url: "review",
    method: "post",
    data
  });
};

export const findAcademy = (data) => {
  return request({
    url: "academy/"+data,
    method: "get"
  });
};

export const memberInfo = () => {
  return request({
    url: "member",
    method: "get"
  });
};

export const memberAcademy = () => {
  return request({
    url: "/academy-management-authority/member",
    method: "get"
  });
};

export const updateAcademy = (data) => {
  return request({
    url: "academy",
    method: "put",
    data
  });
};

export const addSchedule = (data) => {
  return request({
    url: "academy-schedule",
    method: "post",
    data
  });
};

export const deleteSchedule = (data) => {
  return request({
    url: "academy-schedule/"+data,
    method: "delete"
  });
};

export const getSchedule = (data) => {
  return request({
    url: "academy-schedule/academy/"+data,
    method: "get"
  });
};