hibernate {
	cache.use_second_level_cache=false
	cache.use_query_cache=false
	cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
	ci {
		dataSource {
			pooled = true
			driverClassName = "org.hsqldb.jdbcDriver"
			username = "sa"
			password = ""
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:mem:ciDB"
		}
	}
	development {
		dataSource {
			driverClassName = "oracle.jdbc.OracleDriver"
			dialect='org.hibernate.dialect.Oracle9iDialect'
			username = "nsuarez"
			password = "oracle"
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:oracle:thin:@10.0.10.22:1521:desa"
			//			pooled = true
		}
	}
	
	test {
		dataSource {
			pooled = true
			driverClassName = "org.hsqldb.jdbcDriver"
			username = "sa"
			password = ""
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			url = "jdbc:hsqldb:mem:testDB"
		}
	}
}
