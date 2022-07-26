package com.example.vehicledetection;

import com.example.vehicledetection.domain.Camera;
import com.example.vehicledetection.repository.CameraRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CameraServiceTest {
    @Mock
    private CameraRepository cameraRepository;

    @Test
    public void testCamerasSaves(){
        Camera camera = new Camera();
        camera.setModel("camera1");
        camera.setMake("Sony");

        Mockito.when(cameraRepository.save(camera)).thenReturn(camera);
        assertThat("Sony").isEqualTo(camera.getMake());


    }

}
