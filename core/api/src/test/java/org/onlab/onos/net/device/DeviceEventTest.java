package org.onlab.onos.net.device;

import static org.junit.Assert.assertEquals;
import static org.onlab.onos.net.DeviceId.deviceId;

import org.junit.Test;
import org.onlab.onos.event.AbstractEventTest;
import org.onlab.onos.net.DefaultDevice;
import org.onlab.onos.net.DefaultPort;
import org.onlab.onos.net.Device;
import org.onlab.onos.net.Port;
import org.onlab.onos.net.PortNumber;
import org.onlab.onos.net.provider.ProviderId;

/**
 * Tests of the device event.
 */
public class DeviceEventTest extends AbstractEventTest {

    private Device createDevice() {
        return new DefaultDevice(new ProviderId("of", "foo"), deviceId("of:foo"),
                Device.Type.SWITCH, "box", "hw", "sw", "sn");
    }

    @Override
    @Test
    public void withTime() {
        Device device = createDevice();
        Port port = new DefaultPort(device, PortNumber.portNumber(123), true);
        DeviceEvent event = new DeviceEvent(DeviceEvent.Type.DEVICE_ADDED,
                device, port, 123L);
        validateEvent(event, DeviceEvent.Type.DEVICE_ADDED, device, 123L);
        assertEquals("incorrect port", port, event.port());
    }

    @Override
    @Test
    public void withoutTime() {
        Device device = createDevice();
        Port port = new DefaultPort(device, PortNumber.portNumber(123), true);
        long before = System.currentTimeMillis();
        DeviceEvent event = new DeviceEvent(DeviceEvent.Type.DEVICE_ADDED, device);
        long after = System.currentTimeMillis();
        validateEvent(event, DeviceEvent.Type.DEVICE_ADDED, device, before, after);
    }

}
