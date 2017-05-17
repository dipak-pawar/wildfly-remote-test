#### Change standalone.xml
Make sure to start your wildfly on remote server with binding to specific ip(remote-ip) i.e. 10.209.69.207 in my case
by adding following configuration in standalone.xml

```xml
<interfaces>
	<interface name="management">
	    <inet-address value="${jboss.bind.address.management:your-remote-ip}"/>
	</interface>
	<interface name="public">
	    <inet-address value="${jboss.bind.address:your-remote-ip}"/>
	</interface>
</interfaces>
```

### Arquillian configuration

Change values of arquillian.xml for your-remote-ip accordingly.

Start widlfly with offset of value 1 using:

```bash
bin/standalone.sh -Djboss.socket.binding.port-offset=1
```

Run tests using
 ```bash
 mvn clean test
 ```