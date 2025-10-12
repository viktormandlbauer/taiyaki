<script setup lang="ts">
import { computed, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import type { LoginPayload } from './components/LoginForm.vue';
import type { SignupPayload } from './components/SignupForm.vue';

type AuthTab = 'login' | 'signup';

const route = useRoute();
const router = useRouter();

const lastAction = ref('');

const activeTab = computed<AuthTab>(() =>
  route.name === 'login' ? 'login' : 'signup'
);

const headline = computed(() =>
  activeTab.value === 'signup' ? 'Join Taiyaki' : 'Welcome back'
);

const handleSignup = (payload: SignupPayload) => {
  lastAction.value = `Account created for ${payload.fullName}. You can now log in.`;
  router.push({ name: 'login' });
};

const handleLogin = (payload: LoginPayload) => {
  lastAction.value = `Logged in as ${payload.email}.`;
};
</script>

<template>
  <main class="layout">
    <section class="hero">
      <h1>{{ headline }}</h1>
      <p>
        Craft delightful taiyaki experiences. Sign up to start your journey or log in to continue
        where you left off.
      </p>

      <div v-if="lastAction" class="notice">
        {{ lastAction }}
      </div>
    </section>

    <section class="panel">
      <nav class="tabs" role="tablist" aria-label="Authentication pages">
        <RouterLink
          :class="['tab', { active: activeTab === 'signup' }]"
          role="tab"
          :aria-selected="activeTab === 'signup'"
          aria-controls="signup-panel"
          to="/register"
        >
          Sign up
        </RouterLink>
        <RouterLink
          :class="['tab', { active: activeTab === 'login' }]"
          role="tab"
          :aria-selected="activeTab === 'login'"
          aria-controls="login-panel"
          to="/login"
        >
          Log in
        </RouterLink>
      </nav>

      <RouterView v-slot="{ Component }">
        <component
          :is="Component"
          :id="activeTab === 'signup' ? 'signup-panel' : 'login-panel'"
          role="tabpanel"
          :aria-label="activeTab === 'signup' ? 'Sign up form' : 'Login form'"
          @signup="handleSignup"
          @login="handleLogin"
        />
      </RouterView>
    </section>
  </main>
</template>

<style scoped>
.layout {
  min-height: 100dvh;
  display: grid;
  place-items: center;
  padding: 3rem 1.5rem;
  background: radial-gradient(circle at top left, #c7d2fe 0%, rgba(199, 210, 254, 0) 55%),
    radial-gradient(circle at bottom right, #fbcfe8 0%, rgba(251, 207, 232, 0) 55%),
    #f8fafc;
}

.panel {
  width: min(100%, 420px);
  display: grid;
  gap: 1.75rem;
}

.tabs {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  background: rgba(255, 255, 255, 0.9);
  border-radius: 999px;
  padding: 0.4rem;
  gap: 0.4rem;
  box-shadow: inset 0 0 0 1px rgba(148, 163, 184, 0.25);
}

.tab {
  border: none;
  border-radius: 999px;
  padding: 0.7rem;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  background: transparent;
  font-size: 0.95rem;
  font-weight: 600;
  text-decoration: none;
  color: #475569;
  cursor: pointer;
  transition: color 0.2s ease, background 0.2s ease, box-shadow 0.2s ease;
}

.tab.active {
  background: #6366f1;
  color: #ffffff;
  box-shadow: 0 12px 24px -22px rgba(79, 70, 229, 0.8);
}

.hero {
  margin-bottom: 2rem;
  max-width: 520px;
  text-align: center;
}

.hero h1 {
  font-size: clamp(2rem, 2.5vw + 1.5rem, 2.75rem);
  color: #0f172a;
  margin-bottom: 1rem;
}

.hero p {
  margin: 0;
  color: #475569;
  font-size: 1rem;
  line-height: 1.6;
}

.notice {
  margin-top: 1.5rem;
  padding: 0.85rem 1rem;
  border-radius: 0.75rem;
  background: rgba(22, 163, 74, 0.1);
  color: #166534;
  font-weight: 600;
}

@media (min-width: 900px) {
  .layout {
    grid-template-columns: repeat(2, minmax(0, 1fr));
    align-items: center;
    column-gap: 3rem;
  }

  .hero {
    text-align: left;
    margin-bottom: 0;
  }

  .panel {
    width: min(100%, 480px);
  }
}
</style>
