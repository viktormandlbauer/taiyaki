<script setup>
import { RouterLink } from 'vue-router';
</script>

<template>
  <div class="center-wrapper">
    <div class="login-container">
      <h1 style="text-align: center">Login</h1>
      <br>

      <input type="email" class="form-control" placeholder="Email" />
      <input type="password" class="form-control" placeholder="Password" />
      <br>

      <div class="remember-row">
        <label>
          <input type="checkbox" /> Remember me
        </label>
        <a href="#">Forgot password?</a>
      </div>
      <br>
      <RouterLink to="/">
        <div class="d-grid gap-2">
          <button type="submit" class="btn btn-primary">Login</button>
        </div>
      </RouterLink>
      <br>
      <br>

      <p style="text-align: center">Don't have an account? <RouterLink to="/">Register</RouterLink></p>
    </div>
  </div>
</template>

<style scoped>
.center-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 70vh;
}

.login-container {
  width: 400px;
  margin: 0 auto;
  padding: 40px;
  background-color: #e6e6e6;
  border-radius: 10px;
  align-items: center;
}

.remember-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}
</style>
