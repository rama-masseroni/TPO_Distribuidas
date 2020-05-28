USE [CRUCIS]
GO
/****** Object:  Table [dbo].[TurnosEnEspera]    Script Date: 28/05/2020 18:01:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TurnosEnEspera](
	[codEspecialidad] [smallint] NOT NULL,
	[codUsrPac] [int] NOT NULL,
	[codUsrMed] [int] NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[TurnosEnEspera]  WITH CHECK ADD  CONSTRAINT [FK_TurnoEnEspCodEsp_Especialidades] FOREIGN KEY([codEspecialidad])
REFERENCES [dbo].[Especialidades] ([codigo])
GO
ALTER TABLE [dbo].[TurnosEnEspera] CHECK CONSTRAINT [FK_TurnoEnEspCodEsp_Especialidades]
GO
ALTER TABLE [dbo].[TurnosEnEspera]  WITH CHECK ADD  CONSTRAINT [FK_TurnoEnEspCodMed_Medicos] FOREIGN KEY([codUsrMed], [codEspecialidad])
REFERENCES [dbo].[Medicos] ([codigoUsr], [codigoEsp])
GO
ALTER TABLE [dbo].[TurnosEnEspera] CHECK CONSTRAINT [FK_TurnoEnEspCodMed_Medicos]
GO
ALTER TABLE [dbo].[TurnosEnEspera]  WITH CHECK ADD  CONSTRAINT [FK_TurnoEnEspCodPac_Pacientes] FOREIGN KEY([codUsrPac])
REFERENCES [dbo].[Pacientes] ([codigoUsr])
GO
ALTER TABLE [dbo].[TurnosEnEspera] CHECK CONSTRAINT [FK_TurnoEnEspCodPac_Pacientes]
GO
