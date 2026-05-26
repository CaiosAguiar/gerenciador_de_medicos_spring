const API = "http://localhost:8080/api/medicos";

const listaMedicos =
    document.getElementById("listaMedicos");

const modalFormulario =
    document.getElementById("modalFormulario");

const formMedico =
    document.getElementById("formMedico");

const tituloFormulario =
    document.getElementById("tituloFormulario");

// CAMPOS
const campoId =
    document.getElementById("id");

const campoNome =
    document.getElementById("nome");

const campoCrm =
    document.getElementById("crm");

const campoEspecialidade =
    document.getElementById("especialidade");

const campoObservacoes =
    document.getElementById("observacoes");

async function carregarMedicos() {

    try {

        const resposta = await fetch(API);

        if (!resposta.ok) {
            throw new Error("Erro ao carregar médicos");
        }

        const medicos = await resposta.json();

        renderizarMedicos(medicos);

    } catch (erro) {

        listaMedicos.innerHTML = `
            <p>Erro ao carregar médicos.</p>
        `;

        console.error(erro);
    }
}

function renderizarMedicos(medicos) {

    if (medicos.length === 0) {

        listaMedicos.innerHTML = `
            <p>Nenhum médico cadastrado.</p>
            `;
            return;
    }

    let html = "";

    medicos.forEach(medico => {

        html += `
            <div class="card-medico">

                <div clas="info">

                <h3>${medico.nome}</h3>

                <p>
                    <strong>CRM:</strong>
                    ${medico.crm}
                </p>

                <p>
                    <strong>Especialidade:</strong>
                    ${medico.especialidade}
                </p>

                <p>
                    <strong>Cadastro Ativado:</strong>
                    ${medico.cadastroAtivado ? "Sim" : "Não"}
                </p>

                <p>
                    <strong>Observações:</strong>
                    ${medico.observacoes || "Nenhuma"}
                </p>
                
                </div>

                <div class="acoes-card">

                    <button
                        class="btn btn-azul"
                        onclick="editarMedico(${medico.id})">

                        Editar
                    </button>

                    <button
                        class="btn btn-vermelho"
                        onclick="excluirMedico(${medico.id})">

                        Excluir
                    </button>

                </div>

            </div>
        `;
    });

    listaMedicos.innerHTML = html;
}


function abrirFormulario() {

    tituloFormulario.innerText =
        "Incluir Novo Médico";

    formMedico.reset();

    campoId.value = "";

    document.querySelector(
        'input[name="cadastroAtivado"][value="true"]'
    ).checked = true;

    modalFormulario.classList.remove("oculto");
}


function fecharFormulario() {

    modalFormulario.classList.add("oculto");
}


formMedico.addEventListener("submit", async function (event) {

    event.preventDefault();

    const id = campoId.value;

    const medico = {

        nome: campoNome.value,

        crm: campoCrm.value,

        especialidade: campoEspecialidade.value,

        cadastroAtivado:
            document.querySelector(
                'input[name="cadastroAtivado"]:checked'
            ).value === "true",

        observacoes: campoObservacoes.value
    };

    try {

        const metodo = id ? "PUT" : "POST";

        const url = id
            ? `${API}/${id}`
            : API;

        const resposta = await fetch(url, {

            method: metodo,

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(medico)
        });

        if (!resposta.ok) {

            const mensagemErro =
                await resposta.text();

            throw new Error(mensagemErro);
        }

        alert("Médico salvo com sucesso!");

        fecharFormulario();

        carregarMedicos();

    } catch (erro) {

        console.error(erro);

        alert(erro.message);
    }
});


async function editarMedico(id) {

    try {

        const resposta =
            await fetch(`${API}/${id}`);

        if (!resposta.ok) {
            throw new Error("Erro ao buscar médico");
        }

        const medico =
            await resposta.json();

        tituloFormulario.innerText =
            "Alterar Médico";

        campoId.value =
            medico.id;

        campoNome.value =
            medico.nome;

        campoCrm.value =
            medico.crm;

        campoEspecialidade.value =
            medico.especialidade;

        campoObservacoes.value =
            medico.observacoes || "";

        document.querySelector(
            `input[name="cadastroAtivado"][value="${medico.cadastroAtivado}"]`
        ).checked = true;

        modalFormulario.classList.remove("oculto");

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar médico.");
    }
}


async function excluirMedico(id) {

    const confirmar = confirm(
        "Deseja realmente excluir este médico?"
    );

    if (!confirmar) return;

    try {

        const resposta = await fetch(`${API}/${id}`, {

            method: "DELETE"
        });

        if (!resposta.ok) {
            throw new Error("Erro ao excluir médico");
        }

        alert("Médico excluído com sucesso!");

        carregarMedicos();

    } catch (erro) {

        console.error(erro);

        alert("Erro ao excluir médico.");
    }
}

carregarMedicos();