document.addEventListener("DOMContentLoaded", () => {
  const toggleBtn = document.querySelector(".toggle-btn");
  const sidebar = document.getElementById("sidebar");
  const wrapper = document.querySelector(".wrapper");

  toggleBtn.addEventListener("click", () => {
    sidebar.classList.toggle("collapsed");
    wrapper.classList.toggle("collapsed");
  });
});
