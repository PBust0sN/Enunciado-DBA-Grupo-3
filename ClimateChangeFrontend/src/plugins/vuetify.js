import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css'

const vuetify = createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'light',
    themes: {
      light: {
        colors: {
          primary: '#3498db',
          secondary: '#2ecc71',
          accent: '#f39c12',
          error: '#e74c3c',
          info: '#3498db',
          success: '#2ecc71',
          warning: '#f39c12',
        },
      },
    },
  },
  icons: {
    defaultSet: 'mdi',
  },
})

export default vuetify