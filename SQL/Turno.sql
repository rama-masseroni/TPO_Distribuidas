USE [Distribuidas]
GO

/****** Object:  Table [dbo].[Turno]    Script Date: 4/4/2020 19:19:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Turno](
	[fecha] [datetime] NOT NULL,
	[estado] [nchar](16) NOT NULL,
	[paciente] [int] NOT NULL,
	[especialidad] [int] NOT NULL,
	[profesional] [int] NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Turno]  WITH CHECK ADD  CONSTRAINT [FK_dniPac_Turno_Paciente] FOREIGN KEY([paciente])
REFERENCES [dbo].[Paciente] ([dni])
GO

ALTER TABLE [dbo].[Turno] CHECK CONSTRAINT [FK_dniPac_Turno_Paciente]
GO

ALTER TABLE [dbo].[Turno]  WITH CHECK ADD  CONSTRAINT [FK_dniProf_Turno_Profesional] FOREIGN KEY([profesional])
REFERENCES [dbo].[Profesional] ([dni])
GO

ALTER TABLE [dbo].[Turno] CHECK CONSTRAINT [FK_dniProf_Turno_Profesional]
GO

ALTER TABLE [dbo].[Turno]  WITH CHECK ADD  CONSTRAINT [FK_idEspe_Turno_Especialidad] FOREIGN KEY([especialidad])
REFERENCES [dbo].[Especialidad] ([id])
GO

ALTER TABLE [dbo].[Turno] CHECK CONSTRAINT [FK_idEspe_Turno_Especialidad]
GO

