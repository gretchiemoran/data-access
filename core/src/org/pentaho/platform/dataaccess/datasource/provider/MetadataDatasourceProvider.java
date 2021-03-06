/*!
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
* Foundation.
*
* You should have received a copy of the GNU Lesser General Public License along with this
* program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
* or from the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* See the GNU Lesser General Public License for more details.
*
* Copyright (c) 2002-2013 Pentaho Corporation..  All rights reserved.
*/

package org.pentaho.platform.dataaccess.datasource.provider;

import java.util.ArrayList;
import java.util.List;

import org.pentaho.metadata.repository.IMetadataDomainRepository;
import org.pentaho.platform.api.engine.IPentahoSession;
import org.pentaho.platform.dataaccess.api.catalog.IDatasource;
import org.pentaho.platform.dataaccess.api.catalog.IDatasourceProvider;
import org.pentaho.platform.dataaccess.api.catalog.IDatasourceType;
import org.pentaho.platform.dataaccess.impl.catalog.Datasource;
import org.pentaho.platform.dataaccess.impl.catalog.DatasourceType;
import org.pentaho.platform.engine.core.system.PentahoSessionHolder;
import org.pentaho.platform.engine.core.system.PentahoSystem;

public class MetadataDatasourceProvider implements IDatasourceProvider {

  private IMetadataDomainRepository metadataDomainRepository;
  private IDatasourceType datasourceType = new MetadataDatasourceType();
  IPentahoSession pentahoSession = PentahoSessionHolder.getSession();

  public MetadataDatasourceProvider() {
    this.metadataDomainRepository = PentahoSystem.get( IMetadataDomainRepository.class, pentahoSession );
  }

  public MetadataDatasourceProvider( final IMetadataDomainRepository metadataDomainRepository ) {
    this.metadataDomainRepository = metadataDomainRepository;
  }

  @Override
  public List<IDatasource> getDatasources() {
    List<IDatasource> datasources = new ArrayList<IDatasource>();
    for ( String id : metadataDomainRepository.getDomainIds() ) {
      datasources.add( new Datasource( id, (DatasourceType) getType(), null ) );
    }
    return datasources;
  }

  @Override
  public IDatasourceType getType() {
    return datasourceType;
  }

}
