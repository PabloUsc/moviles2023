<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>Agregar claves</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-list>
        <ion-item>
          <ion-input
            label="claves nuevas"
            Type="number"
            min="1"
            :value="claves"
            @ionInput="claves = parseInt($event.target.value)"
          ></ion-input>
        </ion-item>
        <ion-item>
          <ion-button expand="block" @click="agregarClaves()"
            >Agregar claves</ion-button
          >
        </ion-item>
      </ion-list>
      <ion-alert
        :is-open="alerta"
        :backdropDismiss="true"
        :header="titulo"
        :sub-header="subtitulo"
        :message="mensaje"
        :buttons="alertButtons"
      ></ion-alert>

      <!--<ExploreContainer name="Tab 3 page" />-->
    </ion-content>
  </ion-page>
</template>

<script >
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonList,
  IonItem,
  IonLabel,
  IonInput,
  IonButton,
  IonAlert,
} from "@ionic/vue";
import { defineComponent } from "vue";
import { getDatabase, ref, push } from "firebase/database";
export default defineComponent({
  components: {
    IonList,
    IonItem,
    IonLabel,
    IonInput,
    IonButton,
    IonPage,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonAlert,
  },
  data() {
    return {
      claves: 1,
      titulo: "",
      subtitulo: "",
      mensaje: "",
      alerta: false,
    };
  },
  setup() {
    const alertButtons = ["Continuar"];
    return { alertButtons };
  },
  methods: {
    agregarClaves() {
      console.log(this.claves);
      const db = getDatabase();
      var i;
      var errores = 0;
      for (i = 0; i < this.claves; i++) {
        push(ref(db, "claves/"), {
          status: "",
          usuario: "",
        })
          .then(async () => {
            console.log("claves agregadas " + errores + " errores");
            this.titulo = "Claves agregadas";
            this.subtitulo = "Claves agregadas con éxito";
            this.mensaje =
              "Se agregaron " + this.claves.toString() + " claves con éxito";
            this.alerta = true;
          })
          .catch(async (error) => {
            console.log(error);
            errores++;
            this.titulo = "Error en agregar claves";
            this.subtitulo = "Error al agregar claves";
            this.mensaje =
              "Error al agregar " + this.claves.toString() + "claves";
            this.alerta = true;
          });
      }
    },
  },
});
</script>
