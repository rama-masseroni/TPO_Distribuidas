USE [CRUCIS]
GO
/****** Object:  Table [dbo].[Turnos]    Script Date: 28/05/2020 18:01:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Turnos](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[fecha] [date] NOT NULL,
	[hora] [time](7) NOT NULL,
	[codEsp] [smallint] NOT NULL,
	[codUsrMed] [int] NOT NULL,
	[codUsrPac] [int] NULL,
	[codEstado] [char](2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Turnos]  WITH CHECK ADD  CONSTRAINT [FK_TurnoCodEsp_Especialidades] FOREIGN KEY([codEsp])
REFERENCES [dbo].[Especialidades] ([codigo])
GO
ALTER TABLE [dbo].[Turnos] CHECK CONSTRAINT [FK_TurnoCodEsp_Especialidades]
GO
ALTER TABLE [dbo].[Turnos]  WITH CHECK ADD  CONSTRAINT [FK_TurnoCodEstado_EstadosTurno] FOREIGN KEY([codEstado])
REFERENCES [dbo].[EstadosTurno] ([codigo])
GO
ALTER TABLE [dbo].[Turnos] CHECK CONSTRAINT [FK_TurnoCodEstado_EstadosTurno]
GO
ALTER TABLE [dbo].[Turnos]  WITH CHECK ADD  CONSTRAINT [FK_TurnoCodMed_Medicos] FOREIGN KEY([codUsrMed], [codEsp])
REFERENCES [dbo].[Medicos] ([codigoUsr], [codigoEsp])
GO
ALTER TABLE [dbo].[Turnos] CHECK CONSTRAINT [FK_TurnoCodMed_Medicos]
GO
ALTER TABLE [dbo].[Turnos]  WITH CHECK ADD  CONSTRAINT [FK_TurnoCodPac_Pacientes] FOREIGN KEY([codUsrPac])
REFERENCES [dbo].[Pacientes] ([codigoUsr])
GO
ALTER TABLE [dbo].[Turnos] CHECK CONSTRAINT [FK_TurnoCodPac_Pacientes]
GO
