import { ref } from 'vue';

const user = ref(null);

function loadUserFromStorage() {
  const raw = localStorage.getItem('taiyaki_user');
  if (raw) {
    user.value = JSON.parse(raw);
  }
}

loadUserFromStorage();

export function useAuth() {
  const isAuthenticated = ref(!!user.value);

  async function login(email, password) {
    const raw = localStorage.getItem('taiyaki_user_' + email);
    if (!raw) {
      throw new Error('User not found');
    }
    const stored = JSON.parse(raw);
    if (stored.password !== password) {
      throw new Error('Invalid password');
    }
    user.value = { email };
    isAuthenticated.value = true;
    localStorage.setItem('taiyaki_user', JSON.stringify(user.value));
  }

  async function register(email, password) {
    const key = 'taiyaki_user_' + email;
    if (localStorage.getItem(key)) {
      throw new Error('User already exists');
    }
    localStorage.setItem(
      key,
      JSON.stringify({ email, password })
    );
    await login(email, password);
  }

  function logout() {
    user.value = null;
    isAuthenticated.value = false;
    localStorage.removeItem('taiyaki_user');
  }

  return {
    user,
    isAuthenticated,
    login,
    register,
    logout
  };
}
