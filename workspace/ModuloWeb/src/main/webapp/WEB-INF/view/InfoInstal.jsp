
<div class="row">
<!-- INFORMACIÓN DEL CLIENTE -->
	<div class="col-md-10 col-sm-11 col-xs-11"
		style="background-color: yellow">
		<form action="" method="get" accept-charset="utf-8">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-4">
					<div class="form-group">
						<label>Modelo panel</label> <input class="form-control"
							ng-model="c.PanelModel" type="text" name="PanelModel" value=""
							readonly="true">
					</div>
					<div class="form-group">
						<label>Versión panel</label> <input class="form-control"
							readonly="true" type="text" ng-model="c.PanelVersion"
							name="PanelVersion" value="">
					</div>
					<div class="form-group">
						<label>Nº Instalación</label><input class="form-control"
							readonly="true" type="text" ng-model="c.InstallNumber"
							name="InstallNumber" value="">
					</div>
					<div class="form-group optional">
						<label>Cl. con cámaras</label> <input class="form-control"
							ng-model="c.CustCamera" type="text" name="CustCamera" value="">
					</div>
					<div class="form-group optional">
						<label>AKA</label> <input class="form-control" type="text"
							ng-model="c.AKA" name="AKA" value="">
					</div>
				</div>
				<div class="col-md-3 col-sm-3 col-xs-4">
					<div class="form-group">
						<label>Nombre cliente</label><input class="form-control"
							readonly="true" type="text" ng-model="c.CustName" name="CustName"
							value="">
					</div>
					<div class="form-group">
						<label>Email monitoring</label> <input class="form-control"
							readonly="true" type="email" ng-model="c.MonEmail"
							name="MonEmail" value="">
					</div>
					<div class="form-group">
						<label>Email billing</label><input class="form-control"
							readonly="true" type="email" ng-model="c.BillEmail"
							name="BillEmail" value="">
					</div>
					<div class="form-group optional">
						<label>Dirección</label> <input class="form-control" type="text"
							ng-model="c.Adress" name="Adress" value="">
					</div>
					<div class="form-group optional">
						<label>Ciudad</label> <input class="form-control"
							ng-model="c.City" type="text" name="City" value="">
					</div>
				</div>
				<div class="col-md-3 col-sm-3 col-xs-4">
					<div class="form-group">
						<label>Email monitoring 1</label><input class="form-control"
							readonly="true" type="email" ng-model="c.MonEmail1"
							name="MonEmail1" value="">
					</div>
					<div class="form-group">
						<label>Email billing 1</label><input class="form-control"
							readonly="true" type="email" ng-model="c.BillEmail1"
							name="BillEmail1" value="">
					</div>
					<div class="form-group">
						<label>Email servicios</label><input class="form-control"
							readonly="true" type="email" ng-model="c.ServEmail"
							name="ServEmail" value="">
					</div>
					<div class="form-group optional">
						<label>Monitoring Status</label> <input class="form-control"
							type="text" ng-model="c.MonStatus" name="MonStatus" value="">
					</div>
					<div class="form-group optional">
						<label>Segmento</label> <input class="form-control" type="text"
							ng-model="c.Segment" name="Segment" value="">
					</div>
				</div>
				<div class="col-md-3 col-sm-3 col-xs-12 sinPadding">
					<div class="form-group col-md-12 col-sm-12 col-xs-4">
						<label>CCC</label><input class="form-control" readonly="true"
							type="text" ng-model="c.CCC" name="CCC" value="">
					</div>
					<div class="form-group col-md-12 col-sm-12 col-xs-4">
						<label>Fecha act. mail</label><input class="form-control"
							readonly="true" type="text" ng-model="c.DateEmailUp"
							name="DateEmailUp" value="">
					</div>
					<div class="form-group col-md-12 col-sm-12 col-xs-4">
						<label>Teléfono panel</label><input class="form-control"
							readonly="true" ng-model="c.PanelPhone" type="text"
							name="PanelPhone" value="">
					</div>
					<div class="form-group optional col-md-12 col-sm-12 col-xs-4">
						<label>Idioma</label> <input class="form-control" type="text"
							ng-model="c.Lang" name="Lang" value="">
					</div>
					<div class="btn-group btn-group-justified margin-top20">
						<a href="#" class="btn btn-primary">MODIFICAR</a> <a href="#"
							class="btn btn-primary">GUARDAR</a>
						<!-- 											<input class="btn btn-primary" type="button" name="" value="MODIFICAR"> -->
						<!-- 											<input class="btn btn-primary" type="submit" name="" value="GUARDAR"> -->
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- FIN INFORMACIÓN DEL CLIENTE -->
	
	<!-- PANEL LATERAL -->
	<div class="col-md-2 col-sm-1 col-xs-1">
		<!-- PALABRA CLAVE -->
		<div class="row emerge"
			style="background-color: Crimson; min-height: 150px;">
			<div id="palabraClave">
				<label>Palabra Clave</label> <input class="form-control" type="text"
					ng-model="KeyWord" name="KeyWord" value=""> <a href="#"
					class="btn btn-primary">MOSTRAR</a>
			</div>
		</div>
		<!-- FIN PALABRA CLAVE -->
		
		<!-- BÚSQUEDA -->
		<div class="row emerge"
			style="background-color: CornflowerBlue; min-height: 150px">
			<div id="cuadroBusqueda">
				<input class="form-control" type="text" ng-model="Busqueda"
					name="Busqueda" value="" placeholder=""> <label
					class="radio-inline"><input type="radio"
					ng-model="OptSearch" name="OptSearch">eMail</label> <label
					class="radio-inline"><input type="radio"
					ng-model="OptSearch" name="OptSearch">Teléfono</label> <a href="#"
					class="btn btn-primary">Buscar...</a>
			</div>
		</div>
		<!-- FIN BÚSQUEDA -->
	</div>
	<!-- FIN PANEL LATERAL -->
</div>

<!-- PLAN DE ACCIÓN -->
<div class="row"
	style="background-color: DarkMagenta; min-height: 300px"></div>
<!-- FIN PLAN DE ACCIÓN -->

<!-- VISOR -->
<div class="row"
	style="background-color: DarkSeaGreen; min-height: 100px"></div>
<!-- FIN VISOR -->