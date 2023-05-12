<template>
  <ion-app>
    <ion-router-outlet />
  </ion-app>
</template>

<script setup>
import { IonApp, IonRouterOutlet } from '@ionic/vue';
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";

const auth = getAuth();
signInWithEmailAndPassword(auth, "ralejandrobm@gmail.com", "alex00")
.then((userCredential) => {
  // Signed in
  const user = userCredential.user;
  if(user== null)
  {
    console.log("no autenticado") }
  else
  {
    console.log("usuario autenticado", user);
  }
})
</script>
