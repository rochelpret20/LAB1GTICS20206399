package com.lab1.gtics.controller;

import com.lab1.gtics.model.Equipo;

import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;

import java.util.ArrayList; import java.util.List;

@Controller @RequestMapping("/equipos") public class HomeController {

    // Lista estática para almacenar los equipos
    private static final List<Equipo> equipos = new ArrayList<>();


// ==========================================
// MOSTRAR FORMULARIO
// ====================================

    @GetMapping("/nuevo")
    public String mostrarFormulario() {

        return "index";
    }


// ==========================================
// REGISTRAR EQUIPO
// ==========================================

    @PostMapping("/registrar")
    public String registrarEquipo(

            @RequestParam("nombre") String nombre,

            @RequestParam("tipo") String tipo,

            @RequestParam("codigoActivo") String codigoActivo,

            @RequestParam("fechaAdquisicion") String fechaAdquisicion,

            Model model) {


        // Instanciar manualmente el POJO
        //Usando lo del pdf
        /*
        Datos de Ewuipo
        public Equipo(String nombre, String tipo,
                  String codigoActivo, String fechaAdquisicion)
         */
        Equipo equipo = new Equipo("Lewis Rengifo","PC","ABC12354TYU","02/08/26");

        // Asignar valores recibidos
        equipo.setNombre(nombre);
        equipo.setTipo(tipo);
        equipo.setCodigoActivo(codigoActivo);
        equipo.setFechaAdquisicion(fechaAdquisicion);

        // Guardar en la lista
        equipos.add(equipo);


        // Enviar mensaje a la vista
        model.addAttribute("mensaje",
                "Equipo registrado correctamente");


        // Enviar el equipo recién registrado
        model.addAttribute("equipo", equipo);

        return "index";
    }


// ==========================================
// LISTAR EQUIPOS
// ==========================================

    @GetMapping
    public String listarEquipos(Model model) {

        // Enviar la lista a Thymeleaf
        model.addAttribute("equipos", equipos);

        return "equipos";
    }


// ==========================================
// BUSCAR POR CÓDIGO
// ==========================================

    @GetMapping("/buscar")
    public String mostrarBusqueda() {

        return "buscar";
    }


    @GetMapping("/buscar/{codigoActivo}")
    public String buscarPorCodigo(

            @PathVariable("codigoActivo") String codigoActivo,

            Model model) {


        Equipo resultado = null;

        for (Equipo equipo : equipos) {

            if (equipo.getCodigoActivo()
                    .equalsIgnoreCase(codigoActivo)) {

                resultado = equipo;
                break;
            }
        }


        if (resultado != null) {

            model.addAttribute("equipo", resultado);

        } else {

            model.addAttribute("mensaje",
                    "No se encontró ningún equipo con el código: "
                            + codigoActivo);
        }


        return "buscar";
    }
}
