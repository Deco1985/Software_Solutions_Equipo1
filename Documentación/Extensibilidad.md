# Extensibilidad

## Descripción del Atributo de Calidad
La extensibilidad se refiere a la capacidad del sistema para incorporar nuevas funcionalidades o modificar las existentes con facilidad, sin causar un impacto significativo en la estructura original del sistema. La extensibilidad, en el contexto del software,  es la facilidad con la que un sistema o componente de software puede ser modificado para adaptarse a cambios en las especificaciones o en el entorno, o para añadir nuevas funcionalidades.

## Contextualización en el Proyecto
En el proyecto de administración de membresías, la extensibilidad permite que el sistema evolucione con las necesidades del gimnasio, añadiendo nuevas características como tipos de membresías, promociones especiales o integraciones con otras plataformas.

## Desarrollo en el Proyecto
1. **Patrones de Diseño:**
   - **Factory Method:** Facilita la incorporación de nuevos tipos de membresías sin modificar el código existente. Cada nueva membresía se puede añadir mediante la creación de nuevas subclases.
   - **Strategy:** Permite añadir nuevas estrategias de cálculo de precios o políticas de descuento sin afectar a las implementaciones actuales.

2. **Arquitectura Modular:**
   - **Componentes Independientes:** El sistema está compuesto por módulos independientes que pueden ser desarrollados y actualizados de forma autónoma, permitiendo añadir nuevas funcionalidades sin afectar otras partes del sistema.

## Conclusión
La extensibilidad en nuestro proyecto está asegurada mediante una arquitectura modular y el uso de patrones de diseño que permiten incorporar nuevas funcionalidades de manera eficiente. Esto garantiza que el sistema pueda crecer y adaptarse a futuras necesidades sin dificultad.
