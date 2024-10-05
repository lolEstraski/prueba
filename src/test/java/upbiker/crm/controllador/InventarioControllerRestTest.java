package upbiker.crm.controllador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import upbiker.crm.data.Inventario;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InventarioControllerRestTest {

    @Mock
    private Inventario inventario;

    @InjectMocks
    private InventarioController inventarioControllerMock;

    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(inventarioControllerMock)
                .build();
    }

    @Test
    @DisplayName("Debe agregar producto al inventario")
    public void debeAgregarProductoAlInventario() throws Exception{
        var request = new ProductoRequest("Bicicleta Chimano", 150320.0, "CATEGORIA");

        Mockito.when(inventarioControllerMock.agregarProducto(request))
                .thenReturn(inventario.new Producto("Bicicleta Chimano", 150320.0, "CATEGORIA"));

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/inventario/producto")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").exists());
    }

    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}