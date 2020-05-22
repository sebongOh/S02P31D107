const getters = {
    name: (state) => state.student.name,
    email: (state) => state.student.email,
    address: (state) => state.student.address,
    phone: (state) => state.student.phone,
    age: (state) => state.student.age,
    gender: (state) => state.student.gender,
    child: (state) => state.student.child,
};

export default getters;
