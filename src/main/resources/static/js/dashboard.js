// API endpoints
const studentApi = "http://localhost:8080/students";
const teacherApi = "http://localhost:8080/teachers";
const courseApi = "http://localhost:8080/courses";
const registrationApi = "http://localhost:8080/registrations";

// DOM elements
const totalStudents = document.getElementById("totalStudents");
const totalTeachers = document.getElementById("totalTeachers");
const totalCourses = document.getElementById("totalCourses");
const totalRegistrations = document.getElementById("totalRegistrations");

// Fetch and display total students
async function fetchTotalStudents() {
    const res = await fetch(studentApi);
    const students = await res.json();
    totalStudents.textContent = students.length;
}

// Fetch and display total teachers
async function fetchTotalTeachers() {
    const res = await fetch(teacherApi);
    const teachers = await res.json();
    totalTeachers.textContent = teachers.length;
}

// Fetch and display total courses
async function fetchTotalCourses() {
    const res = await fetch(courseApi);
    const courses = await res.json();
    totalCourses.textContent = courses.length;
}

// Fetch and display total registrations
async function fetchTotalRegistrations() {
    const res = await fetch(registrationApi);
    const registrations = await res.json();
    totalRegistrations.textContent = registrations.length;
}

// Initialize dashboard
async function initDashboard() {
    await fetchTotalStudents();
    await fetchTotalTeachers();
    await fetchTotalCourses();
    await fetchTotalRegistrations();
}

// Run
initDashboard();
