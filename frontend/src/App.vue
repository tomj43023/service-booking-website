<template>
  <AppNavbar />
  <main>
    <RouterView />
  </main>
  <AppFooter />
</template>

<script setup>
import AppFooter from '@/components/AppFooter.vue'
import AppNavbar from '@/components/AppNavbar.vue'
</script>

