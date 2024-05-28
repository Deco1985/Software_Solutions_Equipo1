package modelo;

import java.util.HashMap;
import java.util.Map;

public class FabricaMembresias {
    private static final Map<String, Class<? extends Membresia>> tiposDeMembresia = new HashMap<>();

    static {
        tiposDeMembresia.put("Día", MembresiaDia.class);
        tiposDeMembresia.put("Semana", MembresiaSemana.class);
        tiposDeMembresia.put("Mes", MembresiaMes.class);
        tiposDeMembresia.put("Personalizada", MembresiaPersonalizada.class);
    }

    public static Membresia crearMembresia(String tipo, double... parametros) {
        Class<? extends Membresia> clase = tiposDeMembresia.get(tipo);
        if (clase != null) {
            try {
                if ("Personalizada".equals(tipo) && parametros.length > 0) {
                    return new MembresiaPersonalizada(parametros[0]);
                }
                return clase.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Tipo de membresía no soportado");
    }

    public static void registrarMembresia(String nombre, Class<? extends Membresia> clase) {
        tiposDeMembresia.put(nombre, clase);
    }
}
