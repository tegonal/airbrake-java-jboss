airbrake-java-jboss
===================

Airbrake Notifier Java - JBoss 7.x support

JBoss configuration (standalone.xml / host.xml)
-----------------------------------------------

In standalone.xml add:

        [...]
        <subsystem xmlns="urn:jboss:domain:logging:1.1">
            [...]
            <custom-handler name="AIRBRAKE" class="airbrake.JBossAirbrakeHandler" module="com.tegonal.airbrake-java">
                <level name="ERROR"/>
                <properties>
                    <property name="apikey" value="7b339ca7160e52b4dd7332947cf1a5a2"/>
                    <property name="env" value="test-locale"/>
                    <property name="enabled" value="true"/>
                    <property name="url" value="http://errbit.tegonal.com/notifier_api/v2/notices"/>
                </properties>
            </custom-handler>
            [...]
	</subsystem>
	[...]

JBoss configuration (module)
----------------------------

Create [JBOSS_HOME]/modules/com/tegonal/airbrake-java-jboss/main/ directory. Copy airbrake-java-jboss-1.0.0.jar to the directory.
Create the following module.xml-file:

	<module name="com.tegonal.airbrake-java">
		<resources>
			<resource-root path="airbrake-java-jboss-1.0.0.jar"/>
			<resource-root path="airbrake-java-2.2.9.jar"/>
		</resources>
		<dependencies>
			<module name="org.jboss.logmanager"/>
		</dependencies>
	</module>

Support
-------

Send remarks and suggestions to the contact mentioned on [our contact site](http://www.tegonal.com/kontaktformular/).

For any issues, please post then in our [Issues](https://github.com/tegonal/airbrake-java-jboss/issues).
